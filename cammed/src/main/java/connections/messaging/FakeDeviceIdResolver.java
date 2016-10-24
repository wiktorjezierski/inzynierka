package connections.messaging;

/**
 * Created by pdyjak on 18.10.16.
 */
public class FakeDeviceIdResolver implements DeviceIdResolver {
    private static final String ANDROID_EMULATOR = "fPIKlvpAnic:APA91bGlY9Dqm8jwKOOk7zKm90nP4FeteHESjseXDbDsgiAFZKwAc4Ug5wNYzwHLeYUpcv-ADdedyj0bFx9Jk-nKKPo92qtGKhe_dd8OiM_p97aBvlOti5jmi3vue9WBbXdjHRPR_7Xr";
//    private static final String DEVICE = "dwg-S6Jjz5g:APA91bG_8tfBTiaXy3oxxYO_ODlLzEWwvUDfuwmmV3pVc4KhBFzAPgdIMbyIphPF0WqKPqc6zCKF5N50rvgE5-P25PBBfxgQG6afDs7CYBcayao9mCM3aWgOgdh_1MFk5JkGgG0MXSdD";
    private final String DEVICE = "d7L14dlyUxE:APA91bHQ9Br9MxGt2TNlyRYQUo1hnO3LcSlBD3F4jBQLhKNiCgszU8tWLGp1yIL-txujGV56aTcK12IIEmhuCgmGjDkc4N1Y3ofR7e4kbolqKHnUeb9EvKbtWcI_aR3ZIySDvy7oz0RC";

    public String getDeviceIdFor(String userUuid) {
        return DEVICE;
    }
}
