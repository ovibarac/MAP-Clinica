package com.example.clinica;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.events.ChangeEventType;
import com.example.clinica.events.Event;
import com.example.clinica.observer.Observable;
import com.example.clinica.observer.Observer;
import com.example.clinica.repo.ConsultatieRepo;
import com.example.clinica.repo.MedicRepo;
import com.example.clinica.repo.Repository;
import com.example.clinica.repo.SectieRepo;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Service implements Observable<Event> {

    SectieRepo sectieRepo;
    MedicRepo mediciRepo;
    Repository<Integer, Consultatie> consultatieRepo;

    private List<Observer<Event>> observers = new ArrayList<>();

    public Service(){
        sectieRepo = new SectieRepo("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        mediciRepo = new MedicRepo("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        consultatieRepo = new ConsultatieRepo("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
    }

    public List<Sectie> allSectii(){
        return sectieRepo.findAll();
    }

    public List<Medic> allMedici(){
        return mediciRepo.findAll();
    }

    public  List<Consultatie> allConsultatii(){
        return consultatieRepo.findAll();
    }

    public Optional<Consultatie> addConsultatie(Integer id, Integer idMedic, String CNPPacient, String numePacient, LocalDateTime dateTime) {
        //validare

        Optional<Consultatie> consultatie = consultatieRepo.save(new Consultatie(0, idMedic, CNPPacient, numePacient, dateTime));
        notifyObservers(new Event(ChangeEventType.ADD));
        return consultatie;
    }

    public Optional<Consultatie> deleteConsultatie(Integer id){
        Optional<Consultatie> cons = consultatieRepo.delete(id);
        notifyObservers(new Event(ChangeEventType.DELETE));
        return cons;
    }

    /**
     *
     * @return idSectie if Sef, null if not
     */
    public Optional<Integer> getSectieOfSef(Integer medicId){
        Optional<Sectie> s = sectieRepo.findOneFromSef(medicId);
        if(s.isPresent()){
            return Optional.of(s.get().getIdSefDeSectie());
        }
        return Optional.empty();
    }

    public Optional<Medic> getMedic(Integer id){
        return mediciRepo.findOne(id);
    }
    public Optional<Medic> findMedicByNume(String nume){
        return mediciRepo.findByNume(nume);
    }

    @Override
    public void addObserver(Observer<Event> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(Event t) {
        observers.forEach(x->x.update(t));
    }
}
