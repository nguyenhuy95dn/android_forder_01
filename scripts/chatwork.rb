unless ARGV[0].empty?
	require "chatwork"

	# Create message
	ChatWork.api_key = "8f247d4a711fb2326012a8ad47243db3"
	ChatWork::Message.create(room_id: 69661066, body: "[info][To:2391265] [To:2391270] \n\n CI Build Success! \n\n Please Check! \n Link: #{ARGV[0]} [/info]")
end
