package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.exceptions.FormValidationException;
import be.bstorm.akimts.rest.bxl.exceptions.InvalidReferenceException;
import be.bstorm.akimts.rest.bxl.exceptions.UnavailableDatesException;
import be.bstorm.akimts.rest.bxl.mapper.ReservationMapper;
import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationCancellationForm;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationRequestForm;
import be.bstorm.akimts.rest.bxl.repository.EnfantRepository;
import be.bstorm.akimts.rest.bxl.repository.ReservationRepository;
import be.bstorm.akimts.rest.bxl.repository.TuteurRepository;
import be.bstorm.akimts.rest.bxl.service.ReservationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired // Pas recommendé
    private ReservationRepository repository;
    @Autowired // a préférer: injection par constructeur
    private ReservationMapper mapper;
    @Autowired
    private EnfantRepository enfantRepository;
    @Autowired
    private TuteurRepository tuteurRepository;

    public ReservationServiceImpl(ReservationRepository repository, ReservationMapper mapper, EnfantRepository enfantRepository, TuteurRepository tuteurRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.enfantRepository = enfantRepository;
        this.tuteurRepository = tuteurRepository;
    }

    @Override
    public ReservationDTO requestReservation(@NonNull ReservationRequestForm form) {

        if( !dateAvailable(form.getArrive(), form.getDepart()) )
            throw new UnavailableDatesException(form.getArrive(), form.getDepart());

        Reservation reservation = mapper.toEntity(form);

        reservation.setTuteurDepose(
                tuteurRepository.findById(form.getTuteurDepot())
                        .orElseThrow(() -> new InvalidReferenceException(List.of(form.getTuteurDepot())))
        );

        reservation.setTuteurReprend(
                tuteurRepository.findById(form.getTuteurDepart())
                        .orElseThrow(() -> new InvalidReferenceException(List.of(form.getTuteurDepart())))
        );

        reservation.setEnfant(
                enfantRepository.findById(form.getEnfantId())
                        .orElseThrow(() -> new InvalidReferenceException(List.of(form.getEnfantId())))
        );

        return mapper.toDto( repository.save(reservation) );
    }

    @Override
    public boolean dateAvailable(LocalDateTime arrive, LocalDateTime depart) {
        MultiValueMap<String,String> messages = null;
        if( arrive.isAfter(depart) ){
            messages = new LinkedMultiValueMap<>();
            messages.add("arrival/departure", "le moment d'arrive doit être avant le moment de depart");
        }

        if( !arrive.toLocalDate().equals( depart.toLocalDate() ) ){
            messages = messages == null ? new LinkedMultiValueMap<>() : messages;
            messages.add("arrival/departure", "les deux moment doivent correspondre au même jour");
        }

        LocalDateTime endOfThisDay = LocalDate.now().atTime(23,59,59);
        if( arrive.isBefore( endOfThisDay ) ){
            messages = messages == null ? new LinkedMultiValueMap<>() : messages;
            messages.add("arrival", "devrait être future");
        }

        if( depart.isBefore( endOfThisDay ) ){
            messages = messages == null ? new LinkedMultiValueMap<>() : messages;
            messages.add("arrival", "devrait être future");
        }

        // Attention au max 10heures et 59 secondes en realité :/
        double hoursStayed = Duration.between(arrive, depart).toMinutes() / 60.;
        if( hoursStayed < 1 || hoursStayed > 10 ){
            messages = messages == null ? new LinkedMultiValueMap<>() : messages;
            messages.add("arrival/departure", "temps d'accueil entre 1 et 10 heures");
        }

        if( messages != null )
            throw new FormValidationException(messages);

        return repository.checkAvailable(arrive, depart);
    }

    @Override
    public void cancelReservation(@NonNull ReservationCancellationForm form) {
        if( !repository.existsById(form.getId()) )
            throw new ElementNotFoundException(Reservation.class, form.getId());

        repository.cancelReservation(form.getId(), form.getMotif());
    }

    @Override
    public List<ReservationDTO> futureReservOfChild(Long childId) {
        return repository.futurReservOfChild(childId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ReservationDTO> futureOfCurrentMonth() {
        // Alternative 1
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime lastDayOfThisMonth = LocalDateTime.of(
//                now.getYear(),
//                now.getMonth(),
//                now.getMonth().length(now.toLocalDate().isLeapYear()),
//                23,
//                59,
//                59
//        );
//        return repository.findByArriveAfterAndDepartBeforeAndAnnuleFalse(now, lastDayOfThisMonth).stream()
//                .map(mapper::toDto)
//                .toList();

        // Alternative 2
        return repository.findForCurrentMonth().stream()
                .map(mapper::toDto)
                .toList();
    }
}
