package com.uv.inventory_service.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Inventory {
    int id;
    String name;
    Long price;
}
