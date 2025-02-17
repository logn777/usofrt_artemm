package ru.artemm.usofrt.usofrt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.artemm.usofrt.usofrt.services.XlsxService;

@Slf4j
@RestController(value = "api")
@RequiredArgsConstructor
public class XlsxController {

    private final XlsxService xlsxService;

    @Operation(summary = "Найти N-е максимальное число в xlsx файле",
            description = "Возвращает N-е максимальное число из столбца указанного xlsx файла")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный результат",
                    content = {@Content(mediaType = "text/plain", schema = @Schema(type = "string"))}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content)
    })
    @GetMapping("/xlsx/{xlsxFileName}/{n}")
    public Integer getMax(
            @Parameter(description = "Полный путь к xlsx файлу", example = "C:/data/example.xlsx")
            @PathVariable String xlsxFileName,
            @Parameter(description = "N-е максимальное число", example = "3")
            @PathVariable int n) throws IllegalArgumentException {
        return xlsxService.getMaxN(xlsxFileName, n);
    }
}
