package ru.artemm.usofrt.usofrt.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.artemm.usofrt.usofrt.services.XlsxService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class XlsxServiceImpl implements XlsxService {

    @Override
    public Integer getMaxN(String filePath, int n) throws IllegalArgumentException {
        if (Objects.nonNull(filePath) && n > 0) {
            return getNfromXlsx(filePath, n);
        } else {
            log.error("Файл не найден - {}", filePath);
            return 0;
        }
    }

    private Integer getNfromXlsx(String filePath, int n) throws IllegalArgumentException {
        List<Integer> numbers = new ArrayList<>();

        // Чтение xlsx файла
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

             Sheet sheet = workbook.getSheetAt(0); // Берем первый лист
             for (Row row : sheet) {
                 Cell cell = row.getCell(0); // Читаем первый столбец
                 if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                     numbers.add((int) cell.getNumericCellValue());
                 }
            }
        } catch (IOException exception) {
            log.error("Ошибка чтения файла - {}", exception.getMessage());
        }

        if (n > numbers.size()) {
            throw new IllegalArgumentException("N больше количества чисел в файле!");
        }

        return findNthMaxHelper(numbers, n);
    }

    // Метод для поиска N-го максимального числа
    private static int findNthMaxHelper(List<Integer> numbers, int n) {
        return quickSelect(numbers, 0, numbers.size() - 1, numbers.size() - n);
    }

    // Реализация QuickSelect
    private static int quickSelect(List<Integer> nums, int left, int right, int k) {
        if (left == right) {
            return nums.get(left);
        }

        int pivotIndex = partition(nums, left, right);

        if (k == pivotIndex) {
            return nums.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    // Метод для разбиения массива (часть QuickSelect)
    private static int partition(List<Integer> nums, int left, int right) {
        int pivot = nums.get(right);
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums.get(j) <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    // Метод для обмена элементов
    private static void swap(List<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

}
