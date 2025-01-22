package me.unfamousthomas.models;

/**
 * Represents the shutdown request state. Meaning, whether shutdown has been requested.
 * @param requested true if shutdown has been requested by the cluster, false otherwise
 */
public record ShutdownInfoModel(boolean requested) {}
