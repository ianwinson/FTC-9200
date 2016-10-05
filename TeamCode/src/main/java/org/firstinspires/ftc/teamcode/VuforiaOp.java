package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**00
 * Created by Ian on 9/26/2016.
 */
@TeleOp(name="Pushbot: Vuforia", group="Pushbot")
@Disabled
public class VuforiaOp extends LinearOpMode {
    @Override

    public void runOpMode() throws InterruptedException{
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AXs2SUH/////AAAAGQu46+1boERUhcEf4QuMBc2LN2HegZALXMT6ZUb34smqz2dP/D3FKr8TWBYfeRnp2xSM/VP/4vfAUh8ysdmSXrPSCvyywIfauSHMXbtHD+0JqqnTxOg8lC5Jb/BV431eRGzw8IBqgBopDntnUEluYCl7i8FSz2S/kLAcJdkMOOXTBYUV9+zXfLNPSiqB1FJjb92wiTyuNNb11os2xIdg+tLG4D9EnsuGxNUZ7WOypeN2hDaHD06v/Bxf+BQrUyZ9vUXC/4kd6gpfmeAJwPy+nJIGDdXEwTxzbxpVMdIJL0BMFz33b/oni9ba4ZQIr6oDO7q0v/fZ9HCZf1acYST1/f3md7iAEjDvUQR4qclIgvR2";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;


        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(0).setName("Lego");
        beacons.get(0).setName("Gears");

        waitForStart();

        beacons.activate();
        while (opModeIsActive()){
            for(VuforiaTrackable beac : beacons){
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();
                if(pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-Translation", translation);

                    double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));//only if vertical

                    telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);



                }

            }
            telemetry.update();
        }
    }
}
