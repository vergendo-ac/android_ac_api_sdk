package city.augmented.api

import arrow.core.Either

fun printSuccess(message: String) = println("Success: $message")
fun printFailure(message: String) = println("Failure: $message")

fun <A, B> Either<A, B>.ifLeft(invokeLeft: (A) -> Unit): Either<A, B> {
    if (this is Either.Left) invokeLeft(a)
    return this
}

fun <A, B> Either<A, B>.ifRight(invokeRight: (B) -> Unit): Either<A, B> {
    if (this is Either.Right) invokeRight(b)
    return this
}