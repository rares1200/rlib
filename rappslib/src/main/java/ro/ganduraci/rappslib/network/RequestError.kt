package ro.ganduraci.rappslib.network

class RequestError(val id: Int, val message: String, val data: String) {


    override fun toString(): String {
        return "Error with\nid=$id\nmessage:$message\ndata:$data"
    }

    companion object {
        val NETWORK_ERROR = -32098
        val UNKNOWN_ERROR = -32099
    }
}