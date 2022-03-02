package uz.pdp.traindemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.train.modelappbookingflights.Dto.responseDto.TrainResponseDto;
import uz.train.modelappbookingflights.reponse.ApiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrainService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<TrainResponseDto> getTrainListFront() {
        String s = restTemplate.getForObject("http://localhost:8080/trains/available", String.class);
//        System.out.println(s);
        try {
            ApiResponse apiResponse = objectMapper.readValue(s, ApiResponse.class);
            Object data = apiResponse.getData();
            return (List<TrainResponseDto>) data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TrainResponseDto> getTrain(Integer trainId) {
        String s = restTemplate.getForObject("http://localhost:8080/trains/" + trainId, String.class);
        System.out.println(s);
        try {
            ApiResponse apiResponse = objectMapper.readValue(s, ApiResponse.class);
            Object data = apiResponse.getData();
            LinkedHashMap<String, String> data1 = (LinkedHashMap<String, String>) data;
            TrainResponseDto trainResponseDto = new TrainResponseDto();
            trainResponseDto.setTrainNumber(data1.get("trainNumber"));
            trainResponseDto.setName(data1.get("name"));
            trainResponseDto.setFromCity(data1.get("fromCity"));
            trainResponseDto.setToCity(data1.get("toCity"));
            trainResponseDto.setTrainStatus(Boolean.valueOf(String.valueOf(data1.get("trainStatus"))));
            List<TrainResponseDto> trainResponseDtos = new ArrayList<>();
            trainResponseDtos.add(trainResponseDto);
            return trainResponseDtos;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   /* public TrainResponseDto editTrain(String trainNumber) {
        String s = restTemplate.getForObject("http://192.168.1.101:8080/trains/" + trainNumber, String.class);
//        System.out.println(s);
        try {
            ApiResponse apiResponse = objectMapper.readValue(s, ApiResponse.class);
            Object data = apiResponse.getData();
            return (TrainResponseDto) data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
     public List<TrainResponseDto> deleteTrain(String trainNumber) {
        String s = restTemplate.getForObject("http://localhost:8080/trains/delete/" + trainNumber, String.class);
//        System.out.println(s);
        try {
            ApiResponse apiResponse = objectMapper.readValue(s, ApiResponse.class);
            Object data = apiResponse.getData();
            LinkedHashMap<String, String> data1 = (LinkedHashMap<String, String>) data;
            TrainResponseDto trainResponseDto = new TrainResponseDto();
            trainResponseDto.setTrainNumber(data1.get("trainNumber"));
            trainResponseDto.setName(data1.get("name"));
            trainResponseDto.setFromCity(data1.get("fromCity"));
            trainResponseDto.setToCity(data1.get("toCity"));
            trainResponseDto.setTrainStatus(Boolean.valueOf(String.valueOf(data1.get("trainStatus"))));
            List<TrainResponseDto> trainResponseDtos = new ArrayList<>();
            trainResponseDtos.add(trainResponseDto);
            return trainResponseDtos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
