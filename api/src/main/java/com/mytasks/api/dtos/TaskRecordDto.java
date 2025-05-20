package com.mytasks.api.dtos;

import java.util.UUID;

public record TaskRecordDto(String description,
                            UUID user_id) {
}
