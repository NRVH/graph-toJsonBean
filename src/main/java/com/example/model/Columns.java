package com.example.model;

import lombok.Data;

@Data
public class Columns
{
    public String field;
    public boolean exportable;
    public String type;
    public boolean sortable;
    public boolean required;
    public boolean mandatory;
    public String title;
    public String description;
    public Integer order;
}
