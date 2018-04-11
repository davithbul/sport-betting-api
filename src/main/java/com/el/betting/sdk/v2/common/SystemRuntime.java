package com.el.betting.sdk.v2.common;

import java.io.File;

public class SystemRuntime {

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final long BYTE_MEGABYTE = 1024L * 1024L;

    private static Runtime runtime = Runtime.getRuntime();

    public static String osName() {
        return System.getProperty("os.name");
    }

    public static String osVersion() {
        return System.getProperty("os.version");
    }

    public static String osArch() {
        return System.getProperty("os.arch");
    }

    public static double totalMemory() {
        double totalMemory = (double) Runtime.getRuntime().totalMemory() / BYTE_MEGABYTE;
        return round(totalMemory);
    }

    public static double maxMemory() {
        double maxMemory = (double) runtime.maxMemory() / BYTE_MEGABYTE;
        return round(maxMemory);
    }

    public static double usedMemory() {
        double usedMemory = (double) (Runtime.getRuntime().totalMemory() / 1024) / 1024 - (double) (Runtime.getRuntime().freeMemory() / 1024) / 1024;
        return round(usedMemory);
    }

    public static String osInformation() {
        StringBuilder osInformation = new StringBuilder();
        osInformation.append("OS: ").append(osName());
        osInformation.append(LINE_SEPARATOR);
        osInformation.append("Version: ").append(osVersion());
        osInformation.append(LINE_SEPARATOR);
        osInformation.append("Processor: ");
        osInformation.append(osArch());
        osInformation.append(LINE_SEPARATOR);
        osInformation.append("Available processors (cores): ").append(runtime.availableProcessors());
        osInformation.append(LINE_SEPARATOR);
        return osInformation.toString();
    }

    public static String diskInfo() {
        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        StringBuilder distInfo = new StringBuilder();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            distInfo.append("File system root: ").append(root.getAbsolutePath());
            distInfo.append(LINE_SEPARATOR);
            distInfo.append("Total space (bytes): ").append(root.getTotalSpace());
            distInfo.append(LINE_SEPARATOR);
            distInfo.append("Free space (bytes): ").append(root.getFreeSpace());
            distInfo.append(LINE_SEPARATOR);
            distInfo.append("Usable space (bytes): ").append(root.getUsableSpace());
            distInfo.append(LINE_SEPARATOR);
        }
        return distInfo.toString();
    }

    public static String memoryInfo() {
        StringBuilder memoryInfo = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        memoryInfo.append("free memory: ").append(round((double) freeMemory / BYTE_MEGABYTE)).append(" MB");
        memoryInfo.append(LINE_SEPARATOR);
        memoryInfo.append("allocated memory: ").append(round((double) allocatedMemory / BYTE_MEGABYTE)).append(" MB");
        memoryInfo.append(LINE_SEPARATOR);
        memoryInfo.append("max memory: ").append(round((double) maxMemory / BYTE_MEGABYTE)).append(" MB");
        memoryInfo.append(LINE_SEPARATOR);
        memoryInfo.append("total free memory: ").append(round((double) (freeMemory + (maxMemory - allocatedMemory)) / BYTE_MEGABYTE)).append(" MB");
        memoryInfo.append(LINE_SEPARATOR);
        return memoryInfo.toString();
    }

    private static double round(double value) {
        return (double) Math.round(value * 100) / 100;
    }
}
