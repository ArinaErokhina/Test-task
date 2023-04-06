package com.ArinaErochina.Testtask.service;

import com.ArinaErochina.Testtask.Enum.ComparisonOperator;
import com.ArinaErochina.Testtask.model.Sock;

public interface SockWarehouseService {
    void incomeSocks(Sock sock);
    void outcomeSocks(Sock sock);
    public Long allSocks(String color, Long cottonPart, ComparisonOperator operation);
}
