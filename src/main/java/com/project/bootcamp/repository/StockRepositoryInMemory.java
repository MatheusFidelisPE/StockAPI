package com.project.bootcamp.repository;

import com.project.bootcamp.model.dto.StockDTO;

import java.util.List;

public class  StockRepositoryInMemory {

    public static List<StockDTO> listRepository;

    public static List<StockDTO> listAll(){
        return listRepository;
    }
    public static void save (StockDTO dto){
        listRepository.add(dto);
    }
    public static StockDTO getId(Long id){
        return listRepository.stream().filter(x -> x.getId() == id).findFirst().get();
    }

}
