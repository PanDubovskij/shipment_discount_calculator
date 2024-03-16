package com.vinted.entity;

import java.time.LocalDate;

/**
 *  *  This class represent member's transaction
 *
 * @param date Date of the transaction
 * @param size Package size
 * @param provider Shipment provider
 */
public record Transaction(LocalDate date, Size size, Provider provider) {}
