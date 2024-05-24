package spring.demo.haircutter.api;

import org.springframework.http.HttpStatus;
import spring.demo.haircutter.model.CuttersQueue;
import spring.demo.haircutter.service.CuttersQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
public class CuttersQueueController {

    private final CuttersQueueService cuttersQueueService;


    @GetMapping("/admin-and-user/queue")
    public ResponseEntity<List<CuttersQueue>> getTheQueue(){
        return ResponseEntity.ok().body(cuttersQueueService.getTheQueue());
    }


    @GetMapping("/admin/{id}/queue")
    public ResponseEntity<CuttersQueue> getUserById(@PathVariable Integer id)
    {
        CuttersQueue foundUser = cuttersQueueService.getUserById(id);
        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundUser);
    }

    @PostMapping("/admin-and-user/queue")
    public ResponseEntity<CuttersQueue> createUserInQueue(@RequestBody CuttersQueue queue) {
        CuttersQueue createdUser = cuttersQueueService.createUserInQueue(queue);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PutMapping("/user/{id}/queue")
    public ResponseEntity<CuttersQueue> updateUserInQueue(@PathVariable Integer id, @RequestBody CuttersQueue queue)
    {
        CuttersQueue updatedUser = cuttersQueueService.updateUserInQueue(id, queue);
        if(updatedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserInQueue(@PathVariable Integer id)
    {
        boolean deletedUser = cuttersQueueService.deleteUserInQueue(id);
        if (deletedUser) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
