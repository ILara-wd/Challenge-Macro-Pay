package mx.macropay.challenge.data.model.network

interface BaseListResponse<T> {
    var results: List<T>
}
