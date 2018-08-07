package com.nytimes.network;




public class NetworkError extends Throwable {
    private final Throwable error;
    public NetworkError(Throwable e) {
        super(e);
        this.error = e;
    }
    public String getMessage() {
        return error.getMessage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkError that = (NetworkError) o;
        return error != null ? error.equals(that.error) : that.error == null;
    }
    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
