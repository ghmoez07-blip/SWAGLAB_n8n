package Utils;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import nu.pattern.OpenCV;

public class VisualValidation {

    static {
        // Charge la bibliothèque native au démarrage
        OpenCV.loadShared();
    }

    /**
     * Vérifie si une image modèle est présente dans une capture d'écran
     * @return true si la similarité dépasse le seuil (ex: 0.9 pour 90%)
     */
    public static boolean isElementPresent(String screenshotPath, String templatePath, double threshold) {
        Mat source = Imgcodecs.imread(screenshotPath);
        Mat template = Imgcodecs.imread(templatePath);
        Mat result = new Mat();

        // Comparaison par Template Matching
        Imgproc.matchTemplate(source, template, result, Imgproc.TM_CCOEFF_NORMED);

        // Récupération du meilleur score de correspondance
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        System.out.println("Score de similarité détecté : " + mmr.maxVal);

        return mmr.maxVal >= threshold;
    }
}