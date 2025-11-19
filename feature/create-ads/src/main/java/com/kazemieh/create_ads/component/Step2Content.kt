package com.kazemieh.create_ads.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazemieh.create_ads.CreateAdsUiEvent
import com.kazemieh.create_ads.OnAction
import com.kazemieh.domain.model.ads.CreateAdsParam
import com.kazemieh.domain.model.filter.FilterClickType
import com.kazemieh.domain.model.parameter.DataType
import com.kazemieh.domain.model.parameter.Parameter
import com.kazemieh.ui.R
import com.kazemieh.ui.core.filter_item.FilterItem
import com.kazemieh.ui.core.input.AppTextField
import com.kazemieh.ui.core.text.BodyMediumText
import com.kazemieh.ui.extension.immutableListOf
import com.kazemieh.ui.extension.toPrice
import com.kazemieh.ui.theme.AppTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun Step2Content(
    modifier: Modifier,
    createAdsParam: CreateAdsParam,
    onAction: OnAction,
    parameters: ImmutableList<Parameter>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            FilterItem(
                title = stringResource(id = R.string.neighborhood),
                value = createAdsParam.neighborhood?.name,
                onClick = { onAction(CreateAdsUiEvent.OnNeighborhood) }
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                thickness = 0.5.dp
            )
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp, alignment = Alignment.CenterVertically)
            ) {
                BodyMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.price_in_toman)
                )

                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    value = createAdsParam.price,
                    onValueChange = { onAction(CreateAdsUiEvent.OnPriceChanged(it)) },
                    isPrice = true,
                    hint = stringResource(id = R.string.zero)
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 0.5.dp
                )
            }
        }

        items(items = parameters)
        { parameter ->
            when (parameter.dataType) {
                DataType.CheckBoxInput -> {
                    FilterItem(
                        title = parameter.name,
                        value = !parameter.answer.isNullOrEmpty(),
                        onClick = {
                            onAction(CreateAdsUiEvent.OnParameter(parameter))
                        }
                    )
                }

                DataType.FixedOption -> {
                    FilterItem(
                        title = parameter.name,
                        value = parameter.answer,
                        onClick = {
                            onAction(CreateAdsUiEvent.OnParameter(parameter))
                        }
                    )
                }

                else -> {
                    FilterItem(
                        title = parameter.name,
                        value = parameter.answer ?: "",
                        onChangeText = {
                            onAction(CreateAdsUiEvent.OnParameter(parameter.copy(answer = it)))
                        },
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                thickness = 0.5.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        Step2Content(
            modifier = Modifier.fillMaxWidth(),
            createAdsParam = CreateAdsParam(),
            parameters = immutableListOf(),
            onAction = {})
    }
}
