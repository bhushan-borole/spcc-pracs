#define fact(x) ({           \
        int p = 1, i=1;      \
        for(i=1; i<=x; i++){ \
            p *= i;          \
        }                    \
        p;                   \
    })

// ({ }) this is mandatory when writing a multiline macro that has a retval.


#define sum_n(n) n * (n + 1) / 2

#define print_factors(n) ({   \
    for(int i=1; i<=n; i++){  \
        if(n%i == 0){         \
            printf("%d ", i); \
        }                     \
    }                         \
})
