package com.bgabird.droidchat.ui.validator

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}