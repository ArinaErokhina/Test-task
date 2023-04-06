package com.ArinaErochina.Testtask.controller;

import com.ArinaErochina.Testtask.Enum.ComparisonOperator;
import com.ArinaErochina.Testtask.exception.TheParametersAreUncorrectedOrNotFoundException;
import com.ArinaErochina.Testtask.model.Sock;
import com.ArinaErochina.Testtask.service.SockWarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SockWarehouseController {

    private final SockWarehouseService sockWarehouseService;

    public SockWarehouseController(SockWarehouseService sockWarehouseService) {
        this.sockWarehouseService = sockWarehouseService;
    }

    @Operation(summary = "Регистрация прихода носков на склад",
            description = "Регестрирует приход носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Sock.class))),
            @ApiResponse(responseCode = "400", description = "The request parameters are not correct or not found"),
            @ApiResponse(responseCode = "500", description = "An error occurred that is independent of the caller")})
    @PostMapping("/income")
    public void incomeSocks(@RequestBody(required = true) Sock sock) {
        if (sock.getColor() == null || sock.getCottonPart() == null || sock.getQuantity() == null) {
            throw new TheParametersAreUncorrectedOrNotFoundException("Параметры переданы неправильно");
        }
        sockWarehouseService.incomeSocks(sock);
    }

    @Operation(summary = "Регистрация отпуска носков на склад",
            description = "Регестрирует отпуск носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Sock.class))),
            @ApiResponse(responseCode = "400", description = "The request parameters are not correct or not found"),
            @ApiResponse(responseCode = "500", description = "An error occurred that is independent of the caller")})
    @PostMapping("/outcome")
    public void outcomeSocks(@RequestBody(required = true) Sock sock) {
        if (sock.getColor() == null || sock.getCottonPart() == null || sock.getQuantity() == null) {
            throw new TheParametersAreUncorrectedOrNotFoundException("Параметры переданы неправильно");
        }
        sockWarehouseService.outcomeSocks(sock);
    }

    @Operation(summary = "Поиск носков по заданным параметрам",
            description = "Поиск носков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Sock.class))),
            @ApiResponse(responseCode = "400", description = "The request parameters are not correct or not found"),
            @ApiResponse(responseCode = "500", description = "An error occurred that is independent of the caller")})
    @GetMapping
    public ResponseEntity<Long> allSoks(@RequestParam("color") String color,
                                        @RequestParam("cottonPart") Long cottonPart,
                                        @RequestParam("operation") ComparisonOperator operation) {
        if (color == null || cottonPart == null || operation == null) {
            throw new TheParametersAreUncorrectedOrNotFoundException("Переданы неверные параметры.");
        }
        return ResponseEntity.ok(sockWarehouseService.allSocks(color, cottonPart, operation));
    }
}
