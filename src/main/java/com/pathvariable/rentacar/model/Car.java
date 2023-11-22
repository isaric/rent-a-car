package com.pathvariable.rentacar.model;

/**
 * Rental car model
 * @param id - follows the pattern C******
 * @param make - car manufacturer
 * @param model - car model
 */
public record Car(String id, String make, String model) {}
