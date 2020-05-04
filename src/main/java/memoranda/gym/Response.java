package main.java.memoranda.gym;


public class Response<T> {
    private String msg;
    private boolean success;
    private T value;

    /**
     * This constructor is private. Instantiation should be done via
     * Response.failure, or Response.success which invokes the private
     * constructor.
     *
     * @param success The variable that determines whether the query was successful.
     * @param msg The successful/unsuccessful message.
     */
    private Response(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
        this.value = null;
    }


    private Response(boolean success, String msg, T value) {
        this.success = success;
        this.msg = msg;
        this.value = value;
    }

    /**
     * Tests if the response is a failure.
     * @return isFailure
     */
    public boolean isFailure() {
        return !success;
    }

    /**
     * Tests if the response is a success
     * @return isSuccessful
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the response msg, Error or Success string.
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    public T getValue(){
        return this.value;
    }

    /**
     * This static method constructs the msg.
     * @param msg the successful msg.
     * @return returns the response.
     */
    public static Response success(String msg) {
        return new Response(true,msg);
    }

    /**
     * This static method constructs the msg.
     * @param msg the successful msg.
     * @return returns the response.
     */
    public static <T> Response success(String msg, T value) {
        return new Response(true, msg, value);
    }

    /**
     * This static method constructs the msg.
     * @param error is the unsuccessful msg
     * @return returns the response.
     */
    public static Response failure(String error) {
        return new Response(false, error);
    }
}
