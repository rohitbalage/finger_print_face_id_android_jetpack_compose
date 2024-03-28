package com.rrbofficial.faeidandfigureprint

import android.content.Context
import android.icu.text.CaseMap.Title
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity

class BiometricAuthenticator (
  private  val context : Context
    )
{
    private lateinit var  propmtInfo : BiometricPrompt.PromptInfo

    private  val biometricManager = BiometricManager.from(context)

    private lateinit var  biometricPromt : BiometricPrompt


    fun isBiometricAuthAvailable() : BiometricAuthStatus
    {
        return  when(biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG))
        {
            BiometricManager.BIOMETRIC_SUCCESS -> BiometricAuthStatus.READY
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> BiometricAuthStatus.NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> BiometricAuthStatus.TEMPORARY_NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> BiometricAuthStatus.AVAILABLE_BUT_NOT_ENROLLED
            else -> BiometricAuthStatus.NOT_AVAILABLE
        }
    }

    fun promptBiometricAuth (
     title: String,
     subTitle: String,
     negativeArraySizeException: String,
     fragmentActivity: FragmentActivity,
     onSuccess:(result: BiometricPrompt.AuthenticationResult) -> Unit,
     onFailed: () -> Unit,
     onError: (errorCode: Int, errorString: String) -> Unit
     )
    {
        when(isBiometricAuthAvailable())
        {
            BiometricAuthStatus.NOT_AVAILABLE ->
            {
                onError(BiometricAuthStatus.NOT_AVAILABLE.id, "Not available for this device")
                return
            }
            BiometricAuthStatus.TEMPORARY_NOT_AVAILABLE ->
            {
                onError(BiometricAuthStatus.TEMPORARY_NOT_AVAILABLE.id, "Not available at this moment")
                return
            }
            BiometricAuthStatus.AVAILABLE_BUT_NOT_ENROLLED ->
            {
                onError(BiometricAuthStatus.NOT_AVAILABLE.id, "You should add figure print or face id first")
                return
            }
            else ->
            {biometricPromt = BiometricPrompt(
                fragmentActivity,
                object : BiometricPrompt.AuthenticationCallback()
                {

                }
            )

            }
        }
    }

}