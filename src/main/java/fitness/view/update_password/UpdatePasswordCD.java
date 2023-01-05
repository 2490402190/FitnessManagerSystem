package fitness.view.update_password;

public class UpdatePasswordCD extends Thread{
    UpdatePasswordController updatePasswordController;

    public UpdatePasswordCD(UpdatePasswordController updatePasswordController) {
        this.updatePasswordController = updatePasswordController;
    }

    @Override
    public void run() {
        int waitTime = 60;
        while (waitTime!=0){
            updatePasswordController.show(waitTime);
            waitTime--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        updatePasswordController.renew();
    }
}
