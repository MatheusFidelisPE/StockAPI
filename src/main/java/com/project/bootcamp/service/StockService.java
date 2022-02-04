package com.project.bootcamp.service;

import com.project.bootcamp.mapper.MapperStock;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MapperStock mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Stock stock = mapper.toEntity(dto);
        stockRepository.save(stock);
        return mapper.toDto(stock);
    }
}
