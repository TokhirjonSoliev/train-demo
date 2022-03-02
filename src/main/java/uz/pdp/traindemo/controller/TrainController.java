package uz.pdp.traindemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.traindemo.service.TrainService;
import uz.train.modelappbookingflights.Dto.responseDto.TrainResponseDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;

    @GetMapping("/trains")
    public String getTrains(
            Model model
    ){
        List<TrainResponseDto> trainList = trainService.getTrainListFront();
        model.addAttribute("trainList", trainList);
        return "pages/tables";
    }

    @GetMapping("/edit/train")
    public String editTrain(
            Model model
    ){

        return "pages/tables";
    }

    @PostMapping("/train")
    public String getTrain(
            @RequestParam Integer train_id,
            Model model
    ){
        List<TrainResponseDto> trainList = trainService.getTrain(train_id);
        model.addAttribute("trainList", trainList);
        return "pages/train";
    }

    @GetMapping("/deleteTrain/{trainNumber}")
    public String deleteTrain(
            @PathVariable String trainNumber,
            Model model
    ){
        List<TrainResponseDto> trainResponseDtos = trainService.deleteTrain(trainNumber);
//        model.addAttribute("trainResponseDtos", trainResponseDtos);

        return getTrains(model);
    }
}
