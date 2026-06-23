package com.example.planner.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.planner.domain.model.Priority
import com.example.planner.domain.model.Task
import com.example.planner.ui.theme.OchrePrimary
import com.example.planner.ui.theme.OchreContainer
import com.example.planner.ui.theme.PriorityHighColor
import com.example.planner.ui.theme.PriorityLowColor
import com.example.planner.ui.theme.PriorityMediumColor
import com.example.planner.ui.theme.SageSecondary
import com.example.planner.ui.theme.WarmElevated
import com.example.planner.ui.theme.WarmOnSurfaceVariant
import com.example.planner.ui.theme.WarmOutline
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TaskCard(
    task: Task,
    onToggleComplete: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val completed = task.isCompleted
    val priorityColor = when (task.priority) {
        Priority.HIGH -> PriorityHighColor
        Priority.MEDIUM -> PriorityMediumColor
        Priority.LOW -> PriorityLowColor
    }

    val animatedSurface by animateColorAsState(
        targetValue = if (completed) SageSecondary.copy(alpha = 0.12f) else WarmElevated,
        animationSpec = tween(400), label = "cardSurface"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, onClick = onClick
            ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = animatedSurface),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (completed) 0.dp else 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Priority bar
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(42.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        if (completed) SageSecondary.copy(alpha = 0.4f) else priorityColor
                    )
            )

            Spacer(modifier = Modifier.width(14.dp))

            // Complete toggle icon
            IconButton(
                onClick = onToggleComplete,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = if (completed) Icons.Rounded.CheckCircle
                                  else Icons.Rounded.Circle,
                    contentDescription = if (completed) "标记未完成" else "标记完成",
                    modifier = Modifier.size(24.dp),
                    tint = if (completed) SageSecondary else WarmOutline
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (completed) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            else MaterialTheme.colorScheme.onSurface,
                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                    textDecoration = if (completed) TextDecoration.LineThrough else TextDecoration.None
                )
                if (task.note.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.note,
                        style = MaterialTheme.typography.bodySmall,
                        color = WarmOnSurfaceVariant,
                        maxLines = 2, overflow = TextOverflow.Ellipsis
                    )
                }
                task.scheduledDate?.let { date ->
                    Spacer(modifier = Modifier.height(6.dp))
                    val formatter = if (date.year == LocalDate.now().year)
                        DateTimeFormatter.ofPattern("M月d日")
                    else
                        DateTimeFormatter.ofPattern("yyyy年M月d日")
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (completed) SageSecondary.copy(alpha = 0.15f)
                                else OchrePrimary.copy(alpha = 0.1f)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = date.format(formatter),
                            style = MaterialTheme.typography.labelSmall,
                            color = if (completed) SageSecondary.copy(alpha = 0.6f)
                                    else OchrePrimary
                        )
                    }
                }
            }
        }
    }
}
