DESCRIPTION = "Icarus Verilog is a Verilog simulaton and synthesis tool"
HOMEPAGE = "http://iverilog.icarus.com/"
LICENSE = "GPLv2"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/steveicarus/iverilog.git;protocol=https"
SRCREV = "ff8ccc0b3e9c2a23576297dc916ae866ba457b81"

S = "${WORKDIR}/git"

PV = "10.2+git${SRCPV}"

inherit autotools

EXTRA_OEMAKE_append = ' HOSTCC="${BUILD_CC}" HOSTCFLAGS="${BUILD_CFLAGS}"'

# issues with install races
PARALLEL_MAKEINST = ""

# don't regen aclocal or autoheader
EXTRA_AUTORECONF += "--exclude=aclocal --exclude=autoheader"

DEPENDS = " \
	flex-native bison-native \
	gperf-native \
	readline ncurses \
	zlib \
	"

FILES_${PN} += "${libdir}/ivl"

BBCLASSEXTEND = "native nativesdk"
