using System;
using System.Net; // để sử dụng lớp IPAddress, IPEndPoint
using System.Net.Sockets; // để sử dụng lớp Socket
using System.Text; // để sử dụng lớp Encoding

namespace ClientUDP
{
    internal class ClientUDP
    {
        private static IPAddress serverIp = IPAddress.Parse("127.0.0.1");
        private static int serverPort = 1108;
        private static void Main(string[] args)
        {
            Console.Title = "Udp Client";

            // đây là "địa chỉ" của tiến trình server trên mạng
            // mỗi endpoint chứa ip của host và port của tiến trình
            var serverEndpoint = new IPEndPoint(serverIp, serverPort);            

            var size = 4096; // kích thước của bộ đệm
            var receiveBuffer = new byte[size]; // mảng byte làm bộ đệm            

            while (true)
            {
                // yêu cầu người dùng nhập một chuỗi
                Console.ForegroundColor = ConsoleColor.Green;
                Console.Write("# Text >>> ");
                Console.ResetColor();
                var text = Console.ReadLine();

                // khởi tạo object của lớp socket để sử dụng dịch vụ Udp
                // lưu ý SocketType của Udp là Dgram (datagram) 
                var socket = new Socket(SocketType.Dgram, ProtocolType.Udp);
                Console.Write("# Khoi tao socket >>> ");
                // biến đổi chuỗi thành mảng byte
                var sendBuffer = Encoding.ASCII.GetBytes(text);
                Console.Write("# sendBuffer >>> ");
                // gửi mảng byte trên đến tiến trình server
                socket.SendTo(sendBuffer, serverEndpoint);
                Console.Write("# send socket >>> ");

                // endpoint này chỉ dùng khi nhận dữ liệu
                EndPoint dummyEndpoint = new IPEndPoint(IPAddress.Any, 0);
                Console.Write("# Tao dummyEndpoint >>> ");
                // nhận mảng byte từ dịch vụ Udp và lưu vào bộ đệm
                // biến dummyEndpoint có nhiệm vụ lưu lại địa chỉ của tiến trình nguồn
                // tuy nhiên, ở đây chúng ta đã biết tiến trình nguồn là Server
                // do đó dummyEndpoint không có giá trị sử dụng 
                var length = socket.ReceiveFrom(receiveBuffer, ref dummyEndpoint);
                Console.Write("# Tao length >>> ");
                // chuyển đổi mảng byte về chuỗi
                var result = Encoding.ASCII.GetString(receiveBuffer, 0, length);
                Console.Write("# result >>> ");
                // xóa bộ đệm (để lần sau sử dụng cho yên tâm)
                Array.Clear(receiveBuffer, 0, size);

                // đóng socket và giải phóng tài nguyên
                socket.Close();

                // in kết quả ra màn hình
                Console.WriteLine("Result: %s", result.ToString());
            }
        }
    }
}