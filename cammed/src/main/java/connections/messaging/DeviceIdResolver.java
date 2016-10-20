package connections.messaging;

/**
 * Created by pdyjak on 18.10.16.
 */
public interface DeviceIdResolver {
    /**
     * Tries to find device-id for a given user.
     * @param userUuid User's UUID for which corresponding device id should be found.
     * @return A string with valid device id, if has been found for the given UUID, otherwise null.
     */
    String getDeviceIdFor(String userUuid);
}
