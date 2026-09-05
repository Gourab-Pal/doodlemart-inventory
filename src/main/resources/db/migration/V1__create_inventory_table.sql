CREATE TABLE inventory (
                           id UUID NOT NULL,
                           product_id UUID NOT NULL,
                           total_quantity INTEGER NOT NULL DEFAULT 0,
                           reserved_quantity INTEGER NOT NULL DEFAULT 0,
                           created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT inventory_pkey PRIMARY KEY (id),
                           CONSTRAINT inventory_product_id_key UNIQUE (product_id),
                           CONSTRAINT inventory_total_quantity_check
                               CHECK (total_quantity >= 0),
                           CONSTRAINT inventory_reserved_quantity_check
                               CHECK (
                                   reserved_quantity >= 0
                                       AND reserved_quantity <= total_quantity
                                   )
);

CREATE INDEX idx_inventory_product_id
    ON inventory (product_id);