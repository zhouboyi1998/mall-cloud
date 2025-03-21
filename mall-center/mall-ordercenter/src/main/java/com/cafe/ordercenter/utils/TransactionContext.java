package com.cafe.ordercenter.utils;

import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;

import java.util.function.Supplier;

public class TransactionContext {
    // 通过 ThreadLocal 存储当前事务上下文
    private static final ThreadLocal<GlobalTransaction> CURRENT = new ThreadLocal<>();

    // 绑定事务上下文到当前线程
    public static <T> T bind(Supplier<T> supplier) {
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        try {
            CURRENT.set(tx);
            return supplier.get();
        } finally {
            CURRENT.remove();
        }
    }

    // 绑定事务上下文到当前线程
    public static void run(Runnable runnable) {
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        try {
            CURRENT.set(tx);
            runnable.run();
        } finally {
            CURRENT.remove();
        }
    }

    // 在异步线程中绑定父线程的事务上下文
    public static <T> Supplier<T> wrap(Supplier<T> supplier) {
        GlobalTransaction tx = CURRENT.get();
        return () -> {
            try {
                CURRENT.set(tx);
                return supplier.get();
            } finally {
                CURRENT.remove();
            }
        };
    }
}