package pairmatching.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.domain.constants.Course;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;

public class ConfigController {
    private static final String BACKEND_DIR = System.getProperty("user.dir") + "/src/main/resources/backend-crew.md";
    private static final String FRONTEND_DIR = System.getProperty("user.dir") + "/src/main/resources/frontend-crew.md";

    public static Crews configCrews() {
        List<Crew> backendCrews = getBackendCrews();
        List<Crew> frontendCrews = getFrontendCrews();
        return Crews.of(backendCrews, frontendCrews);
    }

    private static List<Crew> getBackendCrews() {
        try (BufferedReader br = new BufferedReader(new FileReader(BACKEND_DIR))) {
            String str;
            List<Crew> backendCrews = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                backendCrews.add(Crew.of(Course.BACKEND, str));
            }
            return backendCrews;
        } catch (IOException e) {
            throw CustomException.from(ErrorMessage.FILE_IO_EXCEPTION);
        }
    }

    private static List<Crew> getFrontendCrews() {
        try (BufferedReader br = new BufferedReader(new FileReader(FRONTEND_DIR))) {
            String str;
            List<Crew> frontendCrews = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                frontendCrews.add(Crew.of(Course.FRONTEND, str));
            }
            return frontendCrews;
        } catch (IOException e) {
            throw CustomException.from(ErrorMessage.FILE_IO_EXCEPTION);
        }
    }
}
