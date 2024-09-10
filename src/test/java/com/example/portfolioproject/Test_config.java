package com.example.portfolioproject;

import com.example.portfolioproject.Exceptions.Exc_entity_already_exist;
import com.example.portfolioproject.Exceptions.Exc_entity_not_found;
import com.example.portfolioproject.Exceptions.Exc_null_data;
import com.example.portfolioproject.Exceptions.Global_exception_handler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@TestConfiguration

// will be used later
public class Test_config{
        @Bean
        public Global_exception_handler globalExceptionHandler() {
            return new Global_exception_handler() {
                public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
                    return new ResponseEntity<>("Custom bad request message", HttpStatus.BAD_REQUEST);
                }

                public ResponseEntity<String> handleEntityNotFoundException(Exc_entity_not_found e) {
                    return new ResponseEntity<>("Custom not found message", HttpStatus.NOT_FOUND);
                }

                public ResponseEntity<String> handleEntityAlreadyExists(Exc_entity_already_exist ex) {
                    return new ResponseEntity<>("Custom conflict message", HttpStatus.CONFLICT);
                }

                public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
                    return new ResponseEntity<>("Custom internal server error message", HttpStatus.INTERNAL_SERVER_ERROR);
                }


                public ResponseEntity<String> handleNullDataException(Exc_null_data ex) {
                    return new ResponseEntity<>("Custom no content message", HttpStatus.NO_CONTENT);
                }
            };
        }
}
