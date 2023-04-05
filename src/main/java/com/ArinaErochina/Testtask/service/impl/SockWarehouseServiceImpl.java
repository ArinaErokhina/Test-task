package com.ArinaErochina.Testtask.service.impl;

import com.ArinaErochina.Testtask.Enum.ComparisonOperator;
import com.ArinaErochina.Testtask.exception.TheParametersAreUncorrectedOrNotFoundException;
import com.ArinaErochina.Testtask.model.Sock;
import com.ArinaErochina.Testtask.repositories.SocksRepository;
import com.ArinaErochina.Testtask.service.SockWarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SockWarehouseServiceImpl implements SockWarehouseService {

    private final SocksRepository socksRepository;

    public SockWarehouseServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    /**
     * Метод производит сохранение новых пар носков, при нахождении такой же позиции в бд прибавляет необходимое количество.
     *
     * @param sock - носки, которые необходимо поставить на приход.
     */
    public void incomeSocks(Sock sock) {
        Sock socksInStock = socksRepository.findSockByCottonPartAndColor(sock.getCottonPart(), sock.getColor());
        if (socksInStock == null) {
            socksRepository.save(sock);
        } else {
            socksInStock.setQuantity(socksInStock.getQuantity() + sock.getQuantity());
            socksRepository.save(socksInStock);
        }
    }

    /**
     * Метод производит сохранение нового значения количества пар носков, в зависимости от передаваемого параметра, при отсутствии данных в бд, выбрасывает ошибку
     * @see TheParametersAreUncorrectedOrNotFoundException
     * @param sock - носки, которые необходимо отпустить
     *
     */
    public void outcomeSocks(Sock sock) {
        Sock socksInStock = socksRepository.findSockByCottonPartAndColor(sock.getCottonPart(), sock.getColor());
        if (socksInStock == null) {
            throw new TheParametersAreUncorrectedOrNotFoundException("Позиция не найдена");
        } else {
            socksInStock.setQuantity(socksInStock.getQuantity() - sock.getQuantity());
            socksRepository.save(socksInStock);
        }
    }

    /**
     * Производит фильтрацию данных в БД на основании передаваемых параметров.
     * @param color - цвет носков
     * @param cottonPart - процент содержания хлопка
     * @param operation - оператор сравнения
     * @return count - количество пар в бд
     */
    public Long allSocks(String color, Long cottonPart, ComparisonOperator operation) {
        List<Sock> socksByColor = socksRepository.findSockByColor(color);
        Long count = 0l;
        if (socksByColor.size() > 0) {
            for (int i = 0; i < socksByColor.size(); i++) {
                Sock sock = socksByColor.get(i);
                if (operation.equals(ComparisonOperator.moreThan) && sock.getCottonPart() > cottonPart) {
                    count += sock.getQuantity();
                }
                if (operation.equals(ComparisonOperator.lessThan) && sock.getCottonPart() < cottonPart) {
                    count += sock.getQuantity();
                }
                if (operation.equals(ComparisonOperator.equals) && sock.getCottonPart() == cottonPart) {
                    count += sock.getQuantity();
                }
            }
        }
        return count;
    }
}

