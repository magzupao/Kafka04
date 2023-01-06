package com.refactorizando.kafka.controller;

import com.refactorizando.kafka.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
public class KafkaController {

  private final KafkaProducer kafkaProducer;

  // http:localhost:8080/api/v1/kafka/publish?message=hello world
  @GetMapping("/publish")
  public ResponseEntity<String> publish(@RequestParam("message") String message){
    if(kafkaProducer!=null){
      kafkaProducer.sendMessage(message);
      return ResponseEntity.ok("Message sent to the topic");
    }else {
      return ResponseEntity.ok("KafkaProducer es null!!");
    }
  }
  @PostMapping("/messages/send")
  public ResponseEntity<String> sendMessage(@RequestBody  String message) {
    kafkaProducer.sendMessage(message);
    return ResponseEntity.ok(message);
  }
}
