package id.boytegar.covidinfo.helper

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
    class Progress(val isLoading: Boolean) : UseCaseResult<Nothing>()
}