Sample Keyboard to repro bugs and issues.

### Issues

* https://code.google.com/p/android/issues/detail?id=210799

### About 210799

    Request request = new Request.Builder().url("https://google.com").get().build();
    OkHttpClient client = new OkHttpClient();
    client.newCall(request).enqueue(new Callback(){
        @Override
        public void onFailure(Call call, final IOException e) {
            // In Battery Saver mode, this block is called.
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(KeyboardService.this, e.getClass().getCanonicalName() + ":" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            // In normal mode, this block is called.
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(KeyboardService.this, "success", Toast.LENGTH_LONG).show();
                }
            });
        }
    });

