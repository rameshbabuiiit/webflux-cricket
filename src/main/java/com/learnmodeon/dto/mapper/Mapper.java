package com.learnmodeon.dto.mapper;

import com.learnmodeon.dto.PlayerDto;
import com.learnmodeon.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    ModelMapper modelMapper = new ModelMapper();

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> T mapToTarget(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
