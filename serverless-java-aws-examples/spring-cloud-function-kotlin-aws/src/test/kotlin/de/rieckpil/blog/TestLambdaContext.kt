package de.rieckpil.blog

import com.amazonaws.services.lambda.runtime.ClientContext
import com.amazonaws.services.lambda.runtime.CognitoIdentity
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger

class TestLambdaContext() : Context {
  override fun getAwsRequestId() = "default"
  override fun getLogGroupName() = "default"
  override fun getLogStreamName() = "default"
  override fun getFunctionName() = "default"
  override fun getFunctionVersion() = "default"
  override fun getInvokedFunctionArn() = "default"
  override fun getRemainingTimeInMillis() = 42
  override fun getMemoryLimitInMB() = 42

  override fun getLogger(): LambdaLogger {
    return TestLogger()
  }

  override fun getIdentity(): CognitoIdentity {
    TODO("Not yet implemented")
  }

  override fun getClientContext(): ClientContext {
    TODO("Not yet implemented")
  }
}

class TestLogger() : LambdaLogger {
  override fun log(message: String?) {
    println(message)
  }

  override fun log(message: ByteArray?) {
    println(message)
  }
}
