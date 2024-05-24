package spring.demo.haircutter.service;

import spring.demo.haircutter.repository.CuttersQueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.demo.haircutter.model.CuttersQueue;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CuttersQueueService {

    private final CuttersQueueRepository cuttersQueueRepository;

    public List<CuttersQueue> getTheQueue(){
        return cuttersQueueRepository.findAll();
    }

    public CuttersQueue getUserById(Integer id){
        return cuttersQueueRepository.findById(id).orElse(null);
    }

    public CuttersQueue createUserInQueue (CuttersQueue queue) {

        CuttersQueue createdCuttersQueue = cuttersQueueRepository.save(queue);
        return createdCuttersQueue;
    }

    public CuttersQueue updateUserInQueue (Integer id, CuttersQueue queue) {
        Optional<CuttersQueue> existingStudent = cuttersQueueRepository.findById(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        CuttersQueue updatedStudent = cuttersQueueRepository.save(queue);
        return updatedStudent;
    }

    public boolean deleteUserInQueue (Integer id) {
        Optional<CuttersQueue> existingStudent = cuttersQueueRepository.findById(id);
        if(existingStudent.isPresent()) {
            cuttersQueueRepository.deleteById(id);
            return true;
        }
        return false;
    }

}