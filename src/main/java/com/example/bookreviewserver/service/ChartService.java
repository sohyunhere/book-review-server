package com.example.bookreviewserver.service;

import com.example.bookreviewserver.model.PostCount;
import com.example.bookreviewserver.repo.PostCountQueryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartService {
    private final PostCountQueryRepo postCountQueryRepo;

    LocalDate today = LocalDate.now();
    List<String> days;

    public List<String> findLabels(){
        days = new ArrayList<>();

        for(int i = 7; i >= 1; i--) {
            LocalDate d = today.minusDays(i);
            days.add(String.valueOf(d));
        }
        return days;
    }

    public List<Integer> literatureData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("문학", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> philosophyData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("철학", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> ssData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("사회과학", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> tsData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("기술과학", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> nsData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("자연과학", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> artData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("예술", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> lanData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("어학", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> hisData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("역사", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
    public List<Integer> exData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){
            PostCount entity = postCountQueryRepo.selectCount("기타", LocalDate.parse(days.get(i)));
            if(entity == null){
                data.add(0);
            }
            else{
                data.add(entity.getCountPost());
            }
        }
        return data;
    }
}
