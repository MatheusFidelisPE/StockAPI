package com.project.bootcamp.mapper;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperStock {

    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();

        stock.setId(dto.getId());
        stock.setDate(dto.getDate());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());

        return stock;
    }

    public StockDTO toDto(Stock stock) {
        StockDTO dto = new StockDTO();

        dto.setId(stock.getId());
        dto.setDate(stock.getDate());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());

        return dto;
    }

    public List<StockDTO> toDtoAll(List<Stock> stockList) {
        List<StockDTO> listStockDTO = stockList.stream().map(x -> this.toDto(x)).collect(Collectors.toList());
        return listStockDTO;
    }
}
