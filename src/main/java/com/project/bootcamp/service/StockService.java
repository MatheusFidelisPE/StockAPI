package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.MapperStock;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private MapperStock mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if (optStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }
    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if (optStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = findById(id);
        repository.deleteById(id);
        return dto;
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll(){
        List<Stock> stockList = repository.findAll();
        return mapper.toDtoAll(stockList);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository
                .findByToday(LocalDate.now())
                .map(mapper::toDtoAll)
                .orElseThrow(NotFoundException::new);
    }
}
