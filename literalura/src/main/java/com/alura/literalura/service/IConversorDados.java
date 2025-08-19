package com.alura.literalura.service;

public interface IConversorDados {
    <T> T conversorDados(String json, Class<T> classe);
}
