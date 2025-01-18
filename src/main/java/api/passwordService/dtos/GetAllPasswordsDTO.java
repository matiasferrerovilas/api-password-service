package api.passwordService.dtos;

import api.passwordService.entities.Tuple;

import java.util.List;

public record GetAllPasswordsDTO(
    String site,
    List<Tuple<String, String>> credentials
) { }
