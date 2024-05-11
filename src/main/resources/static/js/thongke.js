var myChart; // Biến lưu trữ biểu đồ

function setDefaultDate() {
    // Lấy ngày hiện tại
    var today = new Date();

    // Format ngày hiện tại thành yyyy-MM-dd (định dạng mà trường input type="date" yêu cầu)
    var formattedDate = today.toISOString().substr(0, 10);

    // Thiết lập giá trị mặc định cho trường ngày
    document.getElementById("date").value = formattedDate;
}

// Gọi hàm để thiết lập giá trị mặc định khi trang được tải
setDefaultDate();

function formatDate(date) {
    var timestamp;
    if (date) {
        // Chuyển đổi ngày tháng thành đối tượng ngày
        timestamp = new Date(date);
    } else {
        // Nếu không có giá trị ngày tháng, sử dụng ngày hiện tại
        timestamp = new Date();
    }
    
    var formattedDate = "";

    switch (valueRadioBtn()) {
        case "1":
            formattedDate = timestamp.toISOString().substring(0, 10);
            break;
        case "2":
            formattedDate = timestamp.toISOString().substring(0, 7);
            break;
        case "3":
            formattedDate = timestamp.toISOString().substring(0, 4);
            break;
        default:
            formattedDate = "";
            break;
    }

    return formattedDate;
}

function valueRadioBtn() {
    var radBtns = document.getElementsByName('time');
    for (var i = 0; i < radBtns.length; i++) {
        if (radBtns[i].checked) {
            return radBtns[i].value;
        }
    }
    return "";
}

function checkSelectionQuery(){
    var valueSelect = document.getElementById('statistic_Select').value;
    console.log(valueSelect);
    var selectedDate = document.getElementById('date').value; // Lấy giá trị ngày tháng đã chọn

    var formattedDate = formatDate(selectedDate); // Định dạng lại ngày tháng

    if (formattedDate === "") {
        // Code truy xuất dữ liệu không có ngày tháng
        if (valueSelect === "0") {
            SLTVKhoa();
        } else if (valueSelect === "1") {
            // Code vẽ biểu đồ cho ngành
        } else if (valueSelect === "2") {
            // Code vẽ biểu đồ cho thiết bị đã mượn
            SLTBMuon();
        } else {
            // Code vẽ biểu đồ cho vi phạm đã xử lý
            VPDaXuLyTheoNam();
        }
    } else {
        // Code truy xuất dữ liệu có ngày tháng (truyền date = formatted Date)
        $.ajax({
            type: "POST",
            url: "/admin",
            contentType: "application/json",
            data: JSON.stringify({ time: formattedDate }),
            success: function(response) {
                console.log("Success: " + response);
                // Cập nhật dữ liệu của biểu đồ từ phản hồi Ajax
                TBmuonCountByTime = response;

                // Gọi lại hàm tạo biểu đồ để vẽ lại biểu đồ với dữ liệu mới
                SLTBMuonByTime();
            },
            error: function(xhr, status, error) {
                console.error("Error: " + error);
            }
        });
    }
}

function SLTVKhoa(){
    var labels = [];
    var data = [];

    for (var i = 0; i < khoaAndCount.length; i++) {
        var item = khoaAndCount[i];
        labels.push(item[0]); // Tên khoa
        data.push(item[1]);   // Số lượng
    }
    var ctx = document.getElementById('myChart').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels, // Tên khoa
            datasets: [{
                label: 'Số lượng', // Nhãn của dữ liệu
                data: data,        // Dữ liệu
                backgroundColor: 'rgba(54, 162, 235, 1)', // Màu nền của cột
                borderWidth: 0 // Không có đường viền
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true, // Bắt đầu từ 0 trên trục y
                    grid: {
                        display: false // Loại bỏ lưới
                    }
                }
            },
            maintainAspectRatio: false, // Vô hiệu hóa tỷ lệ giữa chiều rộng và chiều cao
            responsive: false, // Vô hiệu hóa tính năng phản ứng
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            width: 400, // Chiều rộng của biểu đồ
            height: 400 // Chiều cao của biểu đồ
        }
    });
}

function SLTBMuon(){
    var labels = [];
    var data = [];

    for (var i = 0; i < TBmuonCount.length; i++) {
        var item = TBmuonCount[i];
        labels.push(item[0]); // Tên thiết bị
        data.push(item[1]);   // Số lần mượn
    }
    var ctx = document.getElementById('myChart').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels, // Tên thiết bị
            datasets: [{
                label: 'Số lần', // Nhãn của dữ liệu
                data: data,        // Dữ liệu
                backgroundColor: 'rgba(54, 162, 235, 1)', // Màu nền của cột
                borderWidth: 0 // Không có đường viền
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true, // Bắt đầu từ 0 trên trục y
                    grid: {
                        display: false // Loại bỏ lưới
                    }
                }
            },
            maintainAspectRatio: false, // Vô hiệu hóa tỷ lệ giữa chiều rộng và chiều cao
            responsive: false, // Vô hiệu hóa tính năng phản ứng
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            width: 400, // Chiều rộng của biểu đồ
            height: 400 // Chiều cao của biểu đồ
        }
    });
}

function SLTBMuonByTime(){
    var labels = [];
    var data = [];
    if (TBmuonCountByTime !== null){
        for (var i = 0; i < TBmuonCountByTime.length; i++) {
            var item = TBmuonCountByTime[i];
            labels.push(item[0]); // Tên thiết bị
            data.push(item[1]);   // Số lần mượn
        }
    }else{
        console.log("không có dữ liệu");
    }
    var ctx = document.getElementById('myChart').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels, // Tên khoa
            datasets: [{
                label: 'Số lượng', // Nhãn của dữ liệu
                data: data,        // Dữ liệu
                backgroundColor: 'rgba(54, 162, 235, 1)', // Màu nền của cột
                borderWidth: 0 // Không có đường viền
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true, // Bắt đầu từ 0 trên trục y
                    grid: {
                        display: false // Loại bỏ lưới
                    }
                }
            },
            maintainAspectRatio: false, // Vô hiệu hóa tỷ lệ giữa chiều rộng và chiều cao
            responsive: false, // Vô hiệu hóa tính năng phản ứng
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            width: 400, // Chiều rộng của biểu đồ
            height: 400 // Chiều cao của biểu đồ
        }
    });
}
function VPDaXuLyTheoNam(){
    /*<![CDATA[*/
    var mydata = dataChart ;
    console.log(mydata);
    
    
    const ctx1 = document.getElementById("Chart1").getContext("2d");
        const chart1 = new Chart(ctx1, {
            type: "line",
            data: {
                labels: [
                    "Jan",
                    "Fab",
                    "Mar",
                    "Apr",
                    "May",
                    "Jun",
                    "Jul",
                    "Aug",
                    "Sep",
                    "Oct",
                    "Nov",
                    "Dec",
                ],
                datasets: [
                    {
                        label: "",
                        backgroundColor: "transparent",
                        borderColor: "#365CF5",
                        data : mydata,
                        pointBackgroundColor: "transparent",
                        pointHoverBackgroundColor: "#365CF5",
                        pointBorderColor: "transparent",
                        pointHoverBorderColor: "#fff",
                        pointHoverBorderWidth: 5,
                        borderWidth: 5,
                        pointRadius: 8,
                        pointHoverRadius: 8,
                        cubicInterpolationMode: "monotone", // Add this line for curved line
                    },
                ],
            },
            options: {
                plugins: {
                    tooltip: {
                        callbacks: {
                            labelColor: function (context) {
                                return {
                                    backgroundColor: "#ffffff",
                                    color: "#171717"
                                };
                            },
                        },
                        intersect: false,
                        backgroundColor: "#f9f9f9",
                        title: {
                            fontFamily: "Plus Jakarta Sans",
                            color: "#8F92A1",
                            fontSize: 12,
                        },
                        body: {
                            fontFamily: "Plus Jakarta Sans",
                            color: "#171717",
                            fontStyle: "bold",
                            fontSize: 16,
                        },
                        multiKeyBackground: "transparent",
                        displayColors: false,
                        padding: {
                            x: 30,
                            y: 10,
                        },
                        bodyAlign: "center",
                        titleAlign: "center",
                        titleColor: "#8F92A1",
                        bodyColor: "#171717",
                        bodyFont: {
                            family: "Plus Jakarta Sans",
                            size: "16",
                            weight: "bold",
                        },
                    },
                    legend: {
                        display: false,
                    },
                },
                responsive: true,
                maintainAspectRatio: false,
                title: {
                    display: false,
                },
                scales: {
                    y: {
                        grid: {
                            display: false,
                            drawTicks: false,
                            drawBorder: false,
                        },
                        ticks: {
                            padding: 35,
                            max: 1200,
                            min: 500,
                        },
                    },
                    x: {
                        grid: {
                            drawBorder: false,
                            color: "rgba(143, 146, 161, .1)",
                            zeroLineColor: "rgba(143, 146, 161, .1)",
                        },
                        ticks: {
                            padding: 20,
                        },
                    },
                },
            },
        });
        /*]]>*/
}


