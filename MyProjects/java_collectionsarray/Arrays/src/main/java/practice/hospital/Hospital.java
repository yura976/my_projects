package practice.hospital;

import java.util.Arrays;

public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        float [] patientsTemperatures = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {
            patientsTemperatures[i] = (float) (Math.round((Math.random() * 8 + 32) * 10.0) / 10.0);
        }
        return patientsTemperatures;
    }

    public static String getReport(float[] temperatureData) {
        float sumTemperature = 0;
        for (float temperatureDatum : temperatureData) {
            sumTemperature += temperatureDatum;
        }
        float averageTemperature = (float) (Math.round(sumTemperature / temperatureData.length * 100.0) / 100.0);

        int countPeoples = 0;
        for (float temperature : temperatureData) {
            if (temperature >= 36.2f && temperature <= 36.9f) {
                countPeoples++;
            }
        }

        return "Температуры пациентов: " + Arrays.toString(temperatureData).replaceAll("[^0-9\\.\\s]", "") +
                "\nСредняя температура: " + averageTemperature +
                "\nКоличество здоровых: " + countPeoples;
    }
}
