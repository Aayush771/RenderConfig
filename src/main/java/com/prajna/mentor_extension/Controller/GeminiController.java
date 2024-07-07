package com.prajna.mentor_extension.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prajna.mentor_extension.Config.AuthUserDetails;
import com.prajna.mentor_extension.DTO.FileFormat;
import com.prajna.mentor_extension.DTO.UserDTO;
import com.prajna.mentor_extension.Entity.Users;
import com.prajna.mentor_extension.Exchanges.Dashboard;
import com.prajna.mentor_extension.Exchanges.GeminiResponseBody;
import com.prajna.mentor_extension.Repository.UsersRepository;
import com.prajna.mentor_extension.Service.GeminiService;

@RestController
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthUserDetails authUserDetails;

    @PostMapping("/fetchDashboard")
    public ResponseEntity<Dashboard> setText(@RequestBody FileFormat fileFormat, @RequestParam String API_KEY)
            throws NoSuchAlgorithmException, IOException {

        Users presentUser = authUserDetails.getCurrentUser();
        ResponseEntity<Dashboard> serviceResponse= geminiService.getDashboard(fileFormat, API_KEY);;
        // if (getSHA256Hash(fileFormat.getContent()) != presentUser.getPresentQueryHash()) {
            // presentUser.setPresentQueryHash(getSHA256Hash(fileFormat.getContent()));
            // presentUser.setDashboard(new Dashboard());
            // usersRepository.save(presentUser);
            
            // ResponseEntity<String> serviceResponse =
            // geminiService.callGemini(fileFormat.getContent(), API_KEY);
        //   serviceResponse  = geminiService.getDashboard(fileFormat, API_KEY);
        //     if (serviceResponse.getStatusCode().is2xxSuccessful() && serviceResponse.getBody() != null) {
               
        //         var oldNumberofQueries = presentUser.getDashboard().getStatus().getNoOfQueries();
        //         var oldInlineCompletions = presentUser.getDashboard().getStatus().getInlineCompletions();

        //         presentUser.getDashboard().getStatus().setNoOfQueries(oldNumberofQueries + 1);

        //         presentUser.getDashboard().getStatus().setInlineCompletions(
        //                 oldInlineCompletions + serviceResponse.getBody().getInlineSuggestion().size());

        //         presentUser.getDashboard().getStatus()
        //                 .setOverallQuality(serviceResponse.getBody().getStatus().getOverAllQuality());

        //         presentUser.getDashboard().setInlineSuggestion(serviceResponse.getBody().getInlineSuggestion());
        //         presentUser.getDashboard().setCheckStyle(serviceResponse.getBody().getCheckStyle());
        //         presentUser.getDashboard().setSummary(serviceResponse.getBody().getSummary());
        //         usersRepository.save(presentUser);

        //         return serviceResponse;
        //     } else {
        //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        //     }
        // } else {
        //     return new ResponseEntity<>(presentUser.getDashboard(), HttpStatus.OK);
        // }
        
        // } 
        return serviceResponse;
    }

    private String getSHA256Hash(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = text.getBytes("UTF-8");
        digest.update(bytes);
        byte[] hash = digest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
